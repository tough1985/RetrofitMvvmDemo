package me.xiba.startlearnmvvm

import org.gradle.api.Project
import org.gradle.api.Plugin

class MvvmPlugin implements Plugin<Project> {
    void apply(Project target){
        target.extensions.create("XibaMvvm", MvvmExtension)

        target.task('generateMvvm', type: MvvmTask){
            group "mvvmGenerator"
        }
    }
}